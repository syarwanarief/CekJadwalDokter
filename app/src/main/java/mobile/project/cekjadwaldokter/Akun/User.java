package mobile.project.cekjadwaldokter.Akun;

class User {

    String DisplayName,NoTelp, EmailUser, Password;
    long createdAt;

    public User(){};
    public User(String displayName,String noTelp, String email, String password, final long createdAt){
        this.DisplayName=displayName;
        this.EmailUser=email;
        this.NoTelp=noTelp;
        this.Password=password;
        this.createdAt=createdAt;
    }

    public String getDisplayName() {
        return DisplayName;
    }
    public String getEmailUser() {
        return EmailUser;
    }
    public long getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return Password;
    }

    public String getNoTelp() {
        return NoTelp;
    }
}
