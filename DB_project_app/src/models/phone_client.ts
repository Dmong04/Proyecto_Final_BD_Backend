import { Client } from "./client";

export class PhoneClient {
    constructor(
        public id: number,
        public phone: string,
        public client: Client
    ) { }
}