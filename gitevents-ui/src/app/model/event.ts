import { Actor } from './actor';
import { Payload } from './payload';


export interface Event {

   id: number;
   type: string;
   isPublic: boolean;
   created_at: string;
   actor: Actor;
   payLoad: Payload;

}
