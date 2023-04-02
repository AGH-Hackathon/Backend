FROM python:3.10

WORKDIR .

COPY requirements.txt ./
COPY main.py main.py
COPY pretrained pretrained

RUN pip install --no-cache-dir -r requirements.txt
RUN pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu118
RUN pip install openai
RUN pip install cloudinary

EXPOSE 8001

CMD ["uvicorn", "main:app", "--port", "8001"]