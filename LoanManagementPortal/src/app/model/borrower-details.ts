import { Address } from "./address";
import { PersonalInformation } from "./personal-information";

export class BorrowerDetails {

    public id!: number;
    public personalInformation!: PersonalInformation;
    public uname!: string;
    public password!: string;
    public phoneno!: string;
    public emailId!: string;
    public borrowerAddress!: Address;
    public enabled!: boolean;
}
