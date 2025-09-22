import dayjs from 'dayjs/esm';
import { IOwner } from 'app/entities/owner/owner.model';
import { IPetType } from 'app/entities/pet-type/pet-type.model';

export interface IPet {
  id: number;
  name?: string | null;
  birthDate?: dayjs.Dayjs | null;
  owner?: Pick<IOwner, 'id'> | null;
  petType?: Pick<IPetType, 'id'> | null;
}

export type NewPet = Omit<IPet, 'id'> & { id: null };
