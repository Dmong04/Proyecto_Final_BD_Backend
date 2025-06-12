import api from "@/api/api"
import { useRouter } from "vue-router"
import { ref } from "vue"

export function useAuth() {
  const username = ref('')
  const password = ref('')
  const error = ref(false)
  const router = useRouter()

  const login = async () => {
    error.value = false
    try {
      const response = await api.post('auth/login', {
        username: username.value,
        password: password.value,
      })
      const role = response.data
      sessionStorage.setItem('ROLE', role)
      router.push('/home')
    } catch (err) {
      error.value = true
    }
  }

  const logout = () => {
    sessionStorage.removeItem('ROLE')
    router.push('/login')
  }

  return {
    username,
    password,
    error,
    login,
    logout
  }
}