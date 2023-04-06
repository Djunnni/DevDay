import React, { useEffect, useState, useRef } from 'react';

import Image from 'next/image';
import Swal from 'sweetalert2';
import { httpForm } from '@/api/http';

import style from './index.module.scss';
import { UPDATE_PROFILE_URL, UPDATE_DEFAULT_PROFILE_URL } from '@/constants';

export const UserAvatar = ({ imageURL, width, height, ...props }) => {
  const inputRef = useRef(null);

  const [imgFile, setImgFile] = useState(imageURL);

  const onClickImageInput = event => {
    event.preventDefault();
    inputRef.current.click();
  };

  const onChange = e => {
    const reader = new FileReader();

    reader.onload = ({ target }) => {
      inputRef.current.src = target.result;
      setImgFile(inputRef.current.files[0]);
    };

    if (!inputRef.current.files[0]) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: '사진을 선택해주세요',
        showConfirmButton: false,
        timer: 1000,
      });
      return;
    }

    reader.readAsDataURL(inputRef.current.files[0]);

    const data = new FormData();
    data.append('profileImg', inputRef.current.files[0] || null);

    httpForm
      .patch(UPDATE_PROFILE_URL, data)
      .then(res => {
        window.location.reload();
      })
      .catch(err => {});
  };

  const updateProfileDefaultImg = () => {
    setImgFile(require('../../image/default-user.png'));

    httpForm
      .patch(UPDATE_DEFAULT_PROFILE_URL)
      .then(res => {
        window.location.reload()
      })
      .catch(err => {});
  };

  return (
    <div className={style.UserAvatar}>
      <Image
        src={imageURL || require('../../image/default-user.png')}
        alt="프로필 이미지"
        className="style.Image rounded-full"
        onClick={onClickImageInput}
        width={width}
        height={height}
      />
      <button onClick={updateProfileDefaultImg}>기본 프로필</button>
      <input
        style={{ display: 'none' }}
        ref={inputRef}
        type="file"
        className={style.ImgInput}
        id="logoImg"
        accept="image/*"
        name="file"
        onChange={onChange}
      />
    </div>
  );
};

UserAvatar.propTypes = {};

UserAvatar.defaultProps = {};
