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
    public address!: string;
    
    getName(): string {
        return `${this.fname} ${this.lname}`;
    }
}
