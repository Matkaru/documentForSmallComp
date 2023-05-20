package org.example.contractor;


public class ContractorsData {
    private long id;
    private String companyName;
    private long nip;
    private long regon;
    private String address;
    private String email;
    private long phoneNumber;
    public ContractorsData() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public long getNip() {
        return nip;
    }
    public void setNip(long nip) {
        this.nip = nip;
    }
    public long getRegon() {
        return regon;
    }
    public void setRegon(long regon) {
        this.regon = regon;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return
                id +
                        "," + companyName +
                        "," + nip +
                        ", " + regon +
                        "," + address +
                        "," + email +
                        ", " + phoneNumber;
   //}
   //public ContractorsData(long id, String companyName, long nip, long regon, String address, String email, long phoneNumber) {
   //    this.id = id;
   //    this.companyName = companyName;
   //    this.nip = nip;
   //    this.regon = regon;
   //    this.address = address;
   //    this.email = email;
   //    this.phoneNumber = phoneNumber;
    }

}
