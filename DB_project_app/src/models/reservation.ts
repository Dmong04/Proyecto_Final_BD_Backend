import { ExtraDetail } from './extra_detail'
import { TourDetail } from './tour_detail'
import { User } from './user'

export class Reservation {
  constructor(
    public id: number,
    public date: Date,
    public time: string,
    public description: string,
    public tourSubtotal: number,
    public extraSubtotal: number,
    public total: number,
    public extraDetail: ExtraDetail | null,
    public tourDetail: TourDetail,
    public user: User
  ) {}
}
