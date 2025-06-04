import api from '@/api/api'

export default {
  getusers() {
    return api.get('/user')
  },

  findUser(username: string) {
    return api.get(`/user/${username}`)
  },
}
