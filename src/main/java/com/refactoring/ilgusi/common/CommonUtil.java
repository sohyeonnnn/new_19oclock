package com.refactoring.ilgusi.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class CommonUtil {


	public static <T> boolean isNull (T param){
		if(param == null){
			return true;
		}else {
			return false;
		}
	}

	//파일 중복체크
	public static String fileRename(String dir, String filename) {
		Path directory = Paths.get(dir);
		String name = filename.substring(0, filename.lastIndexOf('.'));
		String ext = filename.substring(filename.lastIndexOf('.'));

		int count = 0;
		String newName;

		do {
			newName = count == 0 ? name + ext : name + "(" + count + ")" + ext;
			count++;
		} while (Files.exists(directory.resolve(newName)));

		return newName;
	}
}
