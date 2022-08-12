import { environment } from './../../../environments/environment';
export class AbstractService {

  constructor(private resource:string) {}

  protected get baseApi():string {
    return `${environment.baseApi}/${this.resource}`
  }
}
