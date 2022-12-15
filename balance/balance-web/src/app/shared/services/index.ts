export const BASE_API = "/api"

export interface ApiResult {
    success:boolean
    data:any
}

export function handleApiResult(result:ApiResult) {
    if(!result.success) {
        throw new ApplcationError(result.data)
    }
    return result.data
}

export class ApplcationError {
    constructor(public messages:string[]) {}
}
