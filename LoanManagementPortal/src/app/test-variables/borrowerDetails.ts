import { Address } from "../model/address";
import { BorrowerDetails } from "../model/borrower-details";
import { Gender } from "../model/gender";
import { PersonalInformation } from "../model/personal-information";

export const borrowerDetails: BorrowerDetails[] = [
    {
        id: 1,
        personalInformation: new PersonalInformation(),
        uname: 'Admin',
        password: '1234',
        phoneno: '',
        emailId: '',
        borrowerAddress: new Address,
        enabled: true
    },
    {
        id: 2,
        personalInformation: {
            id: 1,
            fname: "Nagulan",
            lname: "R U",
            dateOfBirth: new Date,
            gender: Gender.Male,
            aadhaarCard: "6101 8953 1282",
            panCard: "AIUPU9900B"
        },
        uname: "Nagulan R U",
        password: "1234",
        phoneno: "8870323658",
        emailId: "runagulan88@gmail.com",
        borrowerAddress: {
            id: 1,
            houseNo: "63B-72B",
            street: "Chinnarasingam Street, Vadasery",
            city: "Nagercoil",
            state: "TamilNadu",
            pincode: "629001"
        },
        enabled: true
    }
]