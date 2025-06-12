import { Extra } from './extra'

export class ExtraDetail {
  constructor(
    public id: number,
    public participants: number,
    public price: number,
    public extra: Extra
  ) { }
}
