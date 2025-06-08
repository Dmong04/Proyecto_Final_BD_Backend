import { ExtraDetail } from './extra_detail'
import { TripDetail } from './tour_detail'
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
    public extraDetail: ExtraDetail | null,
    public tripDetail: TripDetail,
    public user: User
  ) {}
}
