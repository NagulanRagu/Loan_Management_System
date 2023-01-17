import { Address } from "./address";

export class BorrowerDetails {

    public id!: number;
    public fname!: string;
    public lname!: string;
    public uname!: string;
    public password!: string;
    public phoneno!: string;
    public emailId!: string;
    public aadhaarCard!: string;
    public panCard!: string;
    public borrowerAddress!: Address;

    getName(): string {
        return `${this.fname} ${this.lname}`;
    }
}
