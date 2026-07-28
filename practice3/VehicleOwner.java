package practice3;

public class VehicleOwner
{
    private final String identityCardNumber;
    private final String fullName;
    private final String email;

    public VehicleOwner(String identityCardNumber, String fullName, String email)
    {
        if (identityCardNumber == null || !identityCardNumber.matches("\\d{12}"))
        {
            throw new IllegalArgumentException("Identity card number must contain exactly 12 digits.");
        }
        if (fullName == null || fullName.isBlank())
        {
            throw new IllegalArgumentException("Owner name must not be blank.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
        {
            throw new IllegalArgumentException("Email format is invalid.");
        }

        this.identityCardNumber = identityCardNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getIdentityCardNumber()
    {
        return identityCardNumber;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return "%s (ID: %s, email: %s)".formatted(fullName, identityCardNumber, email);
    }
}
