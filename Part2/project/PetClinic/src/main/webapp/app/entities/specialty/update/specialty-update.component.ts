import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISpecialty } from '../specialty.model';
import { SpecialtyService } from '../service/specialty.service';
import { SpecialtyFormGroup, SpecialtyFormService } from './specialty-form.service';

@Component({
  standalone: true,
  selector: 'jhi-specialty-update',
  templateUrl: './specialty-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SpecialtyUpdateComponent implements OnInit {
  isSaving = false;
  specialty: ISpecialty | null = null;

  protected specialtyService = inject(SpecialtyService);
  protected specialtyFormService = inject(SpecialtyFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: SpecialtyFormGroup = this.specialtyFormService.createSpecialtyFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specialty }) => {
      this.specialty = specialty;
      if (specialty) {
        this.updateForm(specialty);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const specialty = this.specialtyFormService.getSpecialty(this.editForm);
    if (specialty.id !== null) {
      this.subscribeToSaveResponse(this.specialtyService.update(specialty));
    } else {
      this.subscribeToSaveResponse(this.specialtyService.create(specialty));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecialty>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(specialty: ISpecialty): void {
    this.specialty = specialty;
    this.specialtyFormService.resetForm(this.editForm, specialty);
  }
}
