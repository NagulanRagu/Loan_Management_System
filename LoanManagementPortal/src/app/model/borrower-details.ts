export class BorrowerDetails {

    public fname!: string;
    public lname!: string;
    public uname!: string;
    public password!: string; 
    public phoneno!: string;
    public emailId!: string;
    public address!: string

    // constructor(fname:string, lname:string, uname:string, password:string, phoneno:string, 
    //     emailId:string, address:string){
    //     this.fname = fname;
    //     this.lname = lname;
    //     this.uname = uname;
    //     this.password = password;
    //     this.phoneno = phoneno;
    //     this.emailId = emailId;
    //     this.address = address;
    //     }
    
    getName(): string {
        return `${this.fname} ${this.lname}`;
    }
}
