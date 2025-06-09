import { Extra } from './extra'

export class ExtraDetail {
    constructor(
        public id: number,
        public peopleCount: number,
        public totalPrice: number,
        public extra: Extra
    ) { }
}