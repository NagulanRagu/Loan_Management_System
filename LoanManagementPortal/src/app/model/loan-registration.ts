import { GuarantorInfo } from "./guarantor-info";

export class LoanRegistration {
    
    public id!: number;
    public borrowerId!: number;
    public loanType!: string;
    public loanAmt!: string;
    public paymentPeriod!: number;
    public emiAmt!: string;
    public issuedDate!: Date;
    public guarantorInfo!: GuarantorInfo;
}
