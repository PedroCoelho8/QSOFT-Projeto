import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVisit, NewVisit } from '../visit.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVisit for edit and NewVisitFormGroupInput for create.
 */
type VisitFormGroupInput = IVisit | PartialWithRequiredKeyOf<NewVisit>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVisit | NewVisit> = Omit<T, 'date'> & {
  date?: string | null;
};

type VisitFormRawValue = FormValueOf<IVisit>;

type NewVisitFormRawValue = FormValueOf<NewVisit>;

type VisitFormDefaults = Pick<NewVisit, 'id' | 'date'>;

type VisitFormGroupContent = {
  id: FormControl<VisitFormRawValue['id'] | NewVisit['id']>;
  description: FormControl<VisitFormRawValue['description']>;
  date: FormControl<VisitFormRawValue['date']>;
  pet: FormControl<VisitFormRawValue['pet']>;
};

export type VisitFormGroup = FormGroup<VisitFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VisitFormService {
  createVisitFormGroup(visit: VisitFormGroupInput = { id: null }): VisitFormGroup {
    const visitRawValue = this.convertVisitToVisitRawValue({
      ...this.getFormDefaults(),
      ...visit,
    });
    return new FormGroup<VisitFormGroupContent>({
      id: new FormControl(
        { value: visitRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      description: new FormControl(visitRawValue.description, {
        validators: [Validators.required],
      }),
      date: new FormControl(visitRawValue.date, {
        validators: [Validators.required],
      }),
      pet: new FormControl(visitRawValue.pet),
    });
  }

  getVisit(form: VisitFormGroup): IVisit | NewVisit {
    return this.convertVisitRawValueToVisit(form.getRawValue() as VisitFormRawValue | NewVisitFormRawValue);
  }

  resetForm(form: VisitFormGroup, visit: VisitFormGroupInput): void {
    const visitRawValue = this.convertVisitToVisitRawValue({ ...this.getFormDefaults(), ...visit });
    form.reset(
      {
        ...visitRawValue,
        id: { value: visitRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VisitFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
    };
  }

  private convertVisitRawValueToVisit(rawVisit: VisitFormRawValue | NewVisitFormRawValue): IVisit | NewVisit {
    return {
      ...rawVisit,
      date: dayjs(rawVisit.date, DATE_TIME_FORMAT),
    };
  }

  private convertVisitToVisitRawValue(
    visit: IVisit | (Partial<NewVisit> & VisitFormDefaults),
  ): VisitFormRawValue | PartialWithRequiredKeyOf<NewVisitFormRawValue> {
    return {
      ...visit,
      date: visit.date ? visit.date.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
