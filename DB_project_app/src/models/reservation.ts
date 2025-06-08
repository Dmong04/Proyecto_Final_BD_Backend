
import { User } from './user'

export class Reservation {
  constructor(
    public id: number,
    public date: Date,
    public time: string,
    public description: string,
    public tripSubtotal: number,
    public extraSubtotal: number,
    public total: number,
    public user: User
  ) {}
}