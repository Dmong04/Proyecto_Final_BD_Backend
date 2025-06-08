import { Tour } from './tour'
import { Provider } from './provider'

export class TripDetail {
  constructor(
    public id: number,
    public passengerCount: number,
    public origin: string,
    public destination: string,
    public tour: Tour,
    public provider: Provider
  ) {}
}