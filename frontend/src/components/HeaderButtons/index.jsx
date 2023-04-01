import React, { useState, Fragment } from 'react';

import classNames from 'classnames';
import PropTypes from 'prop-types';

import style from './index.module.scss';

export const HeaderButtons = ({ items, select }) => {
  const [selectedItem, setSelectedItem] = useState(items[0]);

  const handleItemChange = event => {
    setSelectedItem(event.target.value);
    select(event.target.value);
  };

  return (
    <div
      className={classNames(
        style.RadioGroup,
        `flex justify-between items-center`,
      )}
    >
      {items.map((item, index) => (
        <label
          key={index}
          className={classNames(
            selectedItem === item && style.selected,
            `inline-block rounded-2xl px-5 py-2 font-medium text-sm bg-white`,
          )}
        >
          <input
            type="radio"
            name="items"
            value={item}
            checked={selectedItem === item}
            onChange={handleItemChange}
          />
          {item}
        </label>
      ))}
    </div>
  );
};

HeaderButtons.propTypes = {
  items: PropTypes.arrayOf(PropTypes.string),
  select: PropTypes.func,
};

HeaderButtons.defaultProps = {
  select: () => {},
  items: ['전체', '기본', '알고리즘', 'commit'],
};
