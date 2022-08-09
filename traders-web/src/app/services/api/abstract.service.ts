import { environment } from './../../../environments/environment';
export class AbstractService {

  protected getBaseApi(resource:string):string {
    return `${environment.baseApi}/${resource}.json`
  }
}
