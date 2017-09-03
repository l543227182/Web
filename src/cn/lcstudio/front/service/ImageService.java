package cn.lcstudio.front.service;

import cn.lcstudio.bean.UserImage;
import cn.lcstudio.bean.Users;

public interface ImageService {
	public void insertImage(UserImage image);
	public UserImage getHeadPhoto(String UserID);
	public void updateHeadPhoto(UserImage userImage);
	public void deleteImageById(String id);
	public void deleteImageByUserID(String UserID);
}
