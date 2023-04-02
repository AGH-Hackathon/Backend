import io
from typing import List, Dict
from PIL.Image import Image

from constants import OPEN_AI_API_KEY, CLOUDINARY_CLOUD_NAME, CLOUDINARY_API_KEY, CLOUDINARY_API_SECRET

import openai

import torch
from min_dalle import MinDalle

import uvicorn
from fastapi import FastAPI

import cloudinary
from cloudinary.uploader import upload

openai.api_key = OPEN_AI_API_KEY

cloudinary.config(
    cloud_name=CLOUDINARY_CLOUD_NAME,
    api_key=CLOUDINARY_API_KEY,
    api_secret=CLOUDINARY_API_SECRET,
    secure=True
)

app = FastAPI()


def generate_and_parse_labels(num_of_images_and_labels: int) -> List[str]:
    # TODO: uncomment to use Chat-GPT
    # completion = openai.ChatCompletion.create(
    #     model="gpt-3.5-turbo",
    #     messages=[
    #         {
    #             "role": "user",
    #             "content": f"Generate {num_of_images_and_labels} weird descriptions for images that will be generated "
    #                        f"by an AI model and list every description in a new line without enumeration and return "
    #                        f"only it for easy parsing."
    #         }
    #     ]
    # )
    # return [desc for desc in completion.choices[0].message.content.splitlines() if desc]
    # return [
    #     "A giant octopus playing a ukulele and singing a happy tune while floating on a bed of clouds.",
    #     "A giant friendly worm with a big smile, wearing a bowler hat and carrying a briefcase, walking on its hind legs through a busy city street."
    # ]

    return [
        "donald trump",
        "pink cat eating banana",
        "electric snails glow fluorescent",
        "robo octopus dances tango"
    ]


def upload_image(image: Image) -> str:
    with io.BytesIO() as bytes_buffer:
        image.save(bytes_buffer, "png")
        return cloudinary.uploader.upload(bytes_buffer.getvalue())["secure_url"]


def generate_and_upload_images(labels: List[str]) -> List[Dict[str, str]]:
    device: str = "cuda"
    if not torch.cuda.is_available():
        device = "cpu"
        print("CUDA not available. Generating images might take a lot of time!")

    model: MinDalle = MinDalle(
        models_root="./pretrained",
        dtype=torch.float32,
        device=device,
        is_mega=False,
        is_reusable=True
    )

    image_data: List[Dict[str, str]] = []
    for label in labels:
        image: Image = model.generate_image(
            text=label,
            seed=-1,
            grid_size=1,
            is_seamless=False,
            temperature=1,
            top_k=256,
            supercondition_factor=100,
            is_verbose=False
        )

        image_url: str = upload_image(image)
        image_data.append({
            "label": label,
            "url": image_url
        })

    return image_data


@app.get("/generate/{num_of_images_and_labels}")
async def get_image(num_of_images_and_labels: int) -> List[Dict[str, str]]:
    labels: List[str] = generate_and_parse_labels(num_of_images_and_labels)
    image_data: List[Dict[str, str]] = generate_and_upload_images(labels)
    return image_data


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8001)
