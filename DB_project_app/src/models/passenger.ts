import { TourDetail } from './tour_detail'

export class Passenger {
  constructor(
    public id: number,
    public name: string,
    public age: number,
    public tourDetail: TourDetail
  ) { }
}
