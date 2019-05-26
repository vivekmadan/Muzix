import { TestBed } from '@angular/core/testing';

import { AuthenticationserviceService } from './authenticationservice.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


const testdata = {
  username: "test",
  password: "testpass",
  email: "testemail"
};

describe('AuthenticationserviceService', () => {
  let authService: AuthenticationserviceService;
  let httpTestingController: HttpTestingController;
  const testForRegister = "http://localhost:8881/usertrackservice/api/v1/usertrackservice/register";
  const testForLogin = "http://localhost:8881/authenticationsrvice/api/v1/userservice/login";

  beforeEach(() => {
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthenticationserviceService]
    })

    authService = TestBed.get(AuthenticationserviceService);
    httpTestingController = TestBed.get(HttpTestingController);
  
  });

  it('should be created', () => {
    //const service: AuthenticationserviceService = TestBed.get(AuthenticationserviceService);
    expect(authService).toBeTruthy();
  });

  it("#registerUser() should fetch proper response from Http Call", () => {
    authService.registerUser(testdata).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testdata);
    });

    const httpMockReq = httpTestingController.expectOne(testForRegister);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForRegister);
  });

  it("#loginUser() should fetch proper response from Http Call", () => {
    authService.loginUser(testdata).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(testdata);
    });

    const httpMockReq = httpTestingController.expectOne(testForLogin);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForLogin);
  });
});
