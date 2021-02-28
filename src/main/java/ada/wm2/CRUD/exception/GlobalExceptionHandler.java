package ada.wm2.CRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.ModelAndView;
import java.net.BindException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public ModelAndView handle400Errors(Exception ex) {
        ModelAndView modelview = new ModelAndView();
        modelview.setViewName("/errorpages/errorpage-400" );
        modelview.addObject("exception", ex.getMessage());
        return modelview;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ModelAndView handle500Errors(Exception ex) {
        ModelAndView modelview = new ModelAndView();
        modelview.setViewName("/errorpages/errorpage-500" );
        modelview.addObject("exception", ex.getMessage());
        return modelview;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ModelAndView handle405Errors(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errorpages/errorpage-405" );
        mv.addObject("exception", ex.getMessage());
        return mv;
    }


}
