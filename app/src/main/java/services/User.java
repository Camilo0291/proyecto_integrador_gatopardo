package services;


    public class User {
        private final String password;
        private String email_address;

        public User(String email_address, String password) {
            this.email_address = email_address;
            this.password = password;
        }

        public String getEmail_address() {
            return email_address;
        }

        public void setEmail_address(String email_address) {
            this.email_address = email_address;
        }

    }
