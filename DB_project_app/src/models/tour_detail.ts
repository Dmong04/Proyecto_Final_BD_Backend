import { Tour } from './tour'
import { Provider } from './provider'

export class TourDetail {
  constructor(
    public id: number,
    public numPassengers: number,
    public origin: string,
    public destination: string,
    public tour: Tour,
    public provider: Provider
  ) { }
}
