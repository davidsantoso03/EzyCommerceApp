package com.hfad.ezycommerceapp;

public class UserModel {
    public class User{
        private String nama;
        private int nim;

        public User(String nama, int nim) {
            this.nama = nama;
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public int getNim() {
            return nim;
        }

        public void setNim(int nim) {
            this.nim = nim;
        }
    }
}
