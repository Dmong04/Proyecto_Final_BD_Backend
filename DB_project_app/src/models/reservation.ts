import { ExtraDetail } from './extra_detail'
import { TourDetail } from './tour_detail'
import { User } from './user'

export class Reservation {
  constructor(
    public id: number,
    public date: Date,
    public time: string,
    public description: string,
    public tourPrice: number,
    public extraPrice: number,
    public total: number,
    public detailExtra: ExtraDetail | null,
    public detailTour: TourDetail,
    public user: User
  ) { }
}
