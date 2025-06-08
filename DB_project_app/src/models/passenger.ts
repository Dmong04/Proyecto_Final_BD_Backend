import { TripDetail } from './tour_detail'

export class Passenger {
  constructor(
    public id: number,
    public name: string,
    public age: number,
    public tripDetail: TripDetail
  ) {}
}