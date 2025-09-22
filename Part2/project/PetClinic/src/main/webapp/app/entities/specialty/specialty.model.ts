export interface ISpecialty {
  id: number;
  name?: string | null;
}

export type NewSpecialty = Omit<ISpecialty, 'id'> & { id: null };
