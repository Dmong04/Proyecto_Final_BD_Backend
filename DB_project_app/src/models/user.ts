import type { Admin } from './admin'
import { Client } from './client'
export class User {
  constructor(
    public id: number,
    public email: string,
    public username: string,
    public password: string,
    public client: Client,
    public admin: Admin,
  ) {}
}
