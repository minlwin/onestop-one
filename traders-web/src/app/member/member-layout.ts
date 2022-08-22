export interface LayoutMetadata {
  get left():boolean
  get right():boolean
}

export class ThreeColumn implements LayoutMetadata {

  get left(): boolean {
    return true
  }
  get right(): boolean {
    return true
  }
}

export class LeftSideBar implements LayoutMetadata {

  get left(): boolean {
    return true
  }
  get right(): boolean {
    return false
  }
}

export class FullScreen implements LayoutMetadata {
  get left(): boolean {
    return false
  }
  get right(): boolean {
    return false
  }
}

export function isLayoutMetadata(object:any): object is LayoutMetadata {
  return 'left' in object && 'right' in object;
}
