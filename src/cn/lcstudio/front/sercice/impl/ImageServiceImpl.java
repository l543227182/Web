package cn.lcstudio.front.sercice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lcstudio.bean.UserImage;
import cn.lcstudio.bean.Users;
import cn.lcstudio.front.mapper.ImageDao;
import cn.lcstudio.front.service.ImageService;

@Transactional
@Service
public class ImageServiceImpl implements ImageService{

		@Autowired
		private ImageDao imageDao;

		@Override
		public void insertImage(UserImage image) {
			// TODO Auto-generated method stub
			imageDao.insertImage(image);
		}

		@Override
		public UserImage getHeadPhoto(String UserID) {
			// TODO Auto-generated method stub
			return imageDao.getHeadPhoto(UserID);
		}

		@Override
		public void updateHeadPhoto(UserImage userImage) {
			// TODO Auto-generated method stub
			imageDao.updateHeadPhoto(userImage);
		}

		@Override
		public void deleteImageById(String id) {
			// TODO Auto-generated method stub
			imageDao.deleteImageById(id);
		}

		@Override
		public void deleteImageByUserID(String UserID) {
			// TODO Auto-generated method stub
			imageDao.deleteImageByUserID(UserID);
		}
	
}
