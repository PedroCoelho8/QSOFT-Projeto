import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPet, NewPet } from '../pet.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPet for edit and NewPetFormGroupInput for create.
 */
type PetFormGroupInput = IPet | PartialWithRequiredKeyOf<NewPet>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPet | NewPet> = Omit<T, 'birthDate'> & {
  birthDate?: string | null;
};

type PetFormRawValue = FormValueOf<IPet>;

type NewPetFormRawValue = FormValueOf<NewPet>;

type PetFormDefaults = Pick<NewPet, 'id' | 'birthDate'>;

type PetFormGroupContent = {
  id: FormControl<PetFormRawValue['id'] | NewPet['id']>;
  name: FormControl<PetFormRawValue['name']>;
  birthDate: FormControl<PetFormRawValue['birthDate']>;
  owner: FormControl<PetFormRawValue['owner']>;
  petType: FormControl<PetFormRawValue['petType']>;
};

export type PetFormGroup = FormGroup<PetFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PetFormService {
  createPetFormGroup(pet: PetFormGroupInput = { id: null }): PetFormGroup {
    const petRawValue = this.convertPetToPetRawValue({
      ...this.getFormDefaults(),
      ...pet,
    });
    return new FormGroup<PetFormGroupContent>({
      id: new FormControl(
        { value: petRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(petRawValue.name, {
        validators: [Validators.required],
      }),
      birthDate: new FormControl(petRawValue.birthDate, {
        validators: [Validators.required],
      }),
      owner: new FormControl(petRawValue.owner),
      petType: new FormControl(petRawValue.petType),
    });
  }

  getPet(form: PetFormGroup): IPet | NewPet {
    return this.convertPetRawValueToPet(form.getRawValue() as PetFormRawValue | NewPetFormRawValue);
  }

  resetForm(form: PetFormGroup, pet: PetFormGroupInput): void {
    const petRawValue = this.convertPetToPetRawValue({ ...this.getFormDefaults(), ...pet });
    form.reset(
      {
        ...petRawValue,
        id: { value: petRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PetFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      birthDate: currentTime,
    };
  }

  private convertPetRawValueToPet(rawPet: PetFormRawValue | NewPetFormRawValue): IPet | NewPet {
    return {
      ...rawPet,
      birthDate: dayjs(rawPet.birthDate, DATE_TIME_FORMAT),
    };
  }

  private convertPetToPetRawValue(
    pet: IPet | (Partial<NewPet> & PetFormDefaults),
  ): PetFormRawValue | PartialWithRequiredKeyOf<NewPetFormRawValue> {
    return {
      ...pet,
      birthDate: pet.birthDate ? pet.birthDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
