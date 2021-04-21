package ma.youcode.exeption;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ma.youcode.responses.ErrorMessage;

@ControllerAdvice
public class AppExeptionHandler {
	@ExceptionHandler(value = (UserExeption.class))
	public ResponseEntity<Object> HandleUserExeption(UserExeption ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> HandleExeption(Exception ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> ValidExeption(MethodArgumentNotValidException ex, WebRequest request) {

		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->
		errors.put(error.getField(), error.getDefaultMessage()));
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}

}
