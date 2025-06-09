import { Provider } from './provider'

export class PhoneProvider {
    constructor(
        public id: number,
        public phone: string,
        public provider: Provider
    ) { }
}