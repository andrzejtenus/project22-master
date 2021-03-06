import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  constructor(public fb: FormBuilder, public authService: AuthService, public router: Router) {
      this.loginForm = this.fb.group({
      password: ['', [Validators.required]],
      username: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
  }

    loginUser(): void {
      this.authService.signIn(this.loginForm.value).subscribe((res) => {
        this.router.navigate(['/homepage']);
    });
    }
}
