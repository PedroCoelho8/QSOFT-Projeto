export interface IOwner {
  id: number;
  address?: string | null;
  city?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  telephone?: string | null;
}

export type NewOwner = Omit<IOwner, 'id'> & { id: null };
