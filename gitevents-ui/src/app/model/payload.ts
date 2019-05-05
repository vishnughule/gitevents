import { Commit } from './commit';

export interface Payload {
  ref: string;
  commit: Commit;
}
