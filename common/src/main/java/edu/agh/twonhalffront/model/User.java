@Entity
@NoArgsConstructor
class User {
    @Id
    private UUID id;
    private String username;
}