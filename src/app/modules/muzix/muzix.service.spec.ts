import { TestBed } from '@angular/core/testing';

import { MuzixService } from './muzix.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Track } from './track';

const track = {
	"trackId" : "track222",
	"name" : "Track122 Name",
	"artist" : {
		"artistId" : "artist1",
		"name" : "Artist1",
		"image" : {
			"imageId" : 123,
			"size" : "large"
		}
	}
};

const springEndPoint = "http://localhost:8881/usertrackservice/api/v1/usertrackservice/";
let muzixService: MuzixService;
let httpTestingController: HttpTestingController;

describe('MuzixService', () => {
  let track = new Track;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MuzixService]
    })

    muzixService = TestBed.get(MuzixService);
    httpTestingController = TestBed.get(HttpTestingController);

  });

  it('should be created', () => {
    //const service: MuzixService = TestBed.get(MuzixService);
    expect(muzixService).toBeTruthy();
  });

  it('#addTrackToWishList should fetch proper response from Http call', () => {
    muzixService.addTrackToWishList(track).subscribe(res => {
      expect(res.body).toBe(track);
    });

    const url = springEndPoint + "user/" + "test" + "/track";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllTracksFromWishList should fetch proper response from Http call', () => {
    muzixService.getAllTracksForWishlist().subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/tracks";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#deleteTrackFromWishList should fetch proper response from Http call', () => {
    muzixService.deleteTrackFromWishList(track).subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/track";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#updateComments should fetch proper response from Http call', () => {
    muzixService.updateComments(track).subscribe(res => {
      expect(res.body).toBe(track);
    });

    const url = springEndPoint + "user/" + "test" + "/track";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('PATCH');
    expect(httpMockReq.request.url).toEqual(url);
  });

});
