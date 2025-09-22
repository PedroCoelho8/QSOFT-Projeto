import { Component, input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { IVet } from '../vet.model';
import { VetService } from '../service/vet.service';

@Component({
  standalone: true,
  templateUrl: './statement.html',
  imports: [SharedModule, FormsModule],
})
export class VetStatementComponent {
  vet = input<IVet | null>(null);

  previousState(): void {
    window.history.back();
  }
}
