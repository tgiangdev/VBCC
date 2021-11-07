/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: { currentUser?: any | undefined }) {
  const { currentUser } = initialState || {};
  return {
    canAdmin: currentUser && ['ADMIN', 'SYSTEM'].includes(currentUser.role),
    canSystem: currentUser && ['SYSTEM'].includes(currentUser.role)
  };
}
