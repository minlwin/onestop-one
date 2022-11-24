export const environments:Environment = {
  baseApi: "http://localhost:8080"
}

export interface Environment {
  readonly baseApi:string
}
