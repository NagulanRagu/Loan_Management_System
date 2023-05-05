import { Gender } from "./gender";

export class PersonalInformation {

    public id!: number;
    public fname!: string;
    public lname!: string;
    public dateOfBirth!: Date;
    public gender!: Gender;
    public aadhaarCard!: string;
    public panCard!: string;
}
