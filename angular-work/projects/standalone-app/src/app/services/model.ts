export interface Course {
  id: number
  name: string
  duration: number
  level: 'Basic' | 'Intermediate' | 'Advance'
  fees: number
}
