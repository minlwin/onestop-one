import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'profileImage'
})
export class ProfileImagePipe implements PipeTransform {

  transform(value: string | undefined | null): unknown {
    if(value) {
      return `/api/photo/${value}`
    }
    return '/assets/images/profile.png'
  }

}
